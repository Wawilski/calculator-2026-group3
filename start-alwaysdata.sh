#!/bin/sh

set -eu

export JAVA_VERSION="${JAVA_VERSION:-25}"

if [ -f "./app.jar" ]; then
    JAR_PATH="./app.jar"
else
    JAR_PATH=$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*.original' | head -n 1)
fi

if [ -z "${JAR_PATH:-}" ]; then
    echo "No executable jar found (expected ./app.jar or target/*.jar)." >&2
    exit 1
fi

exec java -jar "$JAR_PATH" --server.address="${IP:-::}" --server.port="${PORT:-8080}"
