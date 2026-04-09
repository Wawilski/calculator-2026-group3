#!/bin/sh

set -eu

export JAVA_VERSION="${JAVA_VERSION:-25}"

JAR_PATH=$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*.original' | head -n 1)

if [ -z "${JAR_PATH:-}" ]; then
    echo "No executable jar found in target/. Run mvn package first." >&2
    exit 1
fi

exec java -jar "$JAR_PATH" --server.address="${IP:-::}" --server.port="${PORT:-8080}"
