#!/usr/bin/env bash
set -euo pipefail

OWNER="${1:-Iheidashuai}"
REPO="${2:-buy2sell}"
FULL_NAME="$OWNER/$REPO"

cd "$(dirname "$0")/.."

if ! command -v gh >/dev/null 2>&1; then
  echo "GitHub CLI 'gh' is required. Install it and run 'gh auth login' first." >&2
  exit 1
fi

if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  git init
fi

git add .
if git diff --cached --quiet; then
  echo "No changes to commit."
else
  git commit -m "Initialize buy2sell Java backend starter"
fi

if ! gh repo view "$FULL_NAME" >/dev/null 2>&1; then
  gh repo create "$FULL_NAME" --private --source=. --remote=origin --push
else
  if ! git remote get-url origin >/dev/null 2>&1; then
    git remote add origin "git@github.com:$FULL_NAME.git"
  fi
  git branch -M main
  git push -u origin main
fi

echo "Pushed to https://github.com/$FULL_NAME"
