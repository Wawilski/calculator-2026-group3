const { createApp } = Vue;

createApp({
  data() {
    return {
      expression: "",
      isScienceMenuOpen: false,
      hintMessage: "Ready.",
      result: "",
      resultPretty: "",
      isLoading: false
    };
  },
  mounted() {
    window.addEventListener("keydown", this.handleKeydown);
  },
  beforeUnmount() {
    window.removeEventListener("keydown", this.handleKeydown);
  },
  methods: {
    toggleScienceMenu() {
      this.isScienceMenuOpen = !this.isScienceMenuOpen;
    },

    appendToken(token) {
      if (token === "=") {
        this.evaluateExpression();
        return;
      }
      this.expression += token;
      this.hintMessage = "Expression updated.";
    },

    appendScientificToken(token) {
      this.appendToken(token);
    },

    handleKeydown(event) {
      if (event.ctrlKey || event.altKey || event.metaKey) {
        return;
      }

      const allowedTokens = ["+", "-", "*", "/", "(", ")", "."];

      if (/^\d$/.test(event.key) || allowedTokens.includes(event.key)) {
        event.preventDefault();
        this.appendToken(event.key);
        return;
      }

      if (event.key === "Enter") {
        event.preventDefault();
        this.evaluateExpression();
        return;
      }

      if (event.key === "Backspace") {
        event.preventDefault();
        this.backspace();
        return;
      }

      if (event.key === "Escape" || event.key.toLowerCase() === "c") {
        event.preventDefault();
        this.clearExpression();
      }
    },

    clearExpression() {
      this.expression = "";
      this.result = "";
      this.resultPretty = "";
      this.hintMessage = "Expression cleared.";
    },

    backspace() {
      if (this.expression.length === 0) {
        return;
      }

      this.expression = this.expression.slice(0, -1);
      this.hintMessage = "Expression updated.";
    },

    async evaluateExpression() {
      if (this.isLoading) {
        return;
      }

      const expression = this.expression.trim();
      if (!expression) {
        this.result = "";
        this.resultPretty = "";
        this.hintMessage = "Enter an expression first.";
        return;
      }

      this.isLoading = true;
      this.hintMessage = "Calculating...";

      try {
        const response = await fetch("/api/evaluate-text", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ expression: this.normalizeExpression(expression) })
        });

        const payload = await this.readApiPayload(response);
        if (!response.ok) {
          this.result = "";
          this.resultPretty = "";
          this.hintMessage = this.formatApiError(payload);
          return;
        }

        this.result = payload.result ?? "";
        this.resultPretty = payload.pretty ?? "";
        this.hintMessage = "Calculation done.";
      } catch (_) {
        this.result = "";
        this.resultPretty = "";
        this.hintMessage = "Unable to reach the API.";
      } finally {
        this.isLoading = false;
      }
    },

    normalizeExpression(expression) {
      return expression.replace(/\^/g, "**");
    },

    formatApiError(payload) {
      if (!payload || typeof payload !== "object") {
        return "Request failed.";
      }
      if (Array.isArray(payload.details) && payload.details.length > 0) {
        return payload.details[0];
      }
      if (payload.rawText) {
        return payload.rawText;
      }
      return payload.message || "Request failed.";
    },

    async readApiPayload(response) {
      const contentType = response.headers.get("content-type") || "";
      if (contentType.includes("application/json")) {
        return response.json();
      }

      const rawText = await response.text();
      return {
        message: response.ok ? "Unexpected response format." : `HTTP ${response.status}`,
        rawText: rawText ? rawText.slice(0, 200) : ""
      };
    }
  }
}).mount("#app");
