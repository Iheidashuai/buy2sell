#!/usr/bin/env bash
set -euo pipefail

java -version

if [ ! -x ./mvnw ]; then
  echo "mvnw is missing or not executable" >&2
  exit 1
fi

./mvnw -v
