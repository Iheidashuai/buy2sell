#!/usr/bin/env bash
set -euo pipefail

cd "${CLAUDE_PROJECT_DIR:-$(pwd)}"

if [ -x ./mvnw ]; then
  ./mvnw -q -DskipTests=false test
else
  echo "mvnw not found or not executable" >&2
  exit 1
fi
