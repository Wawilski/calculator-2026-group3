const { createApp } = Vue;

createApp({
  data() {
    return {
      expression: "",
      isScienceMenuOpen: false,
      hintMessage: "No calculation is done in this GUI."
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
      this.expression += token;
      this.hintMessage = "Expression captured only (frontend demo).";
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
      this.hintMessage = "Expression cleared.";
    },

    backspace() {
      if (this.expression.length === 0) {
        return;
      }

      this.expression = this.expression.slice(0, -1);
      this.hintMessage = "Expression captured only (frontend demo).";
    }
  }
}).mount("#app");
