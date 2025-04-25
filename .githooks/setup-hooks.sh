#!/bin/bash

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

echo "🔧 Setting up pre-commit tools..."

# Configure git to use our hooks
git config core.hooksPath .githooks

# Install yamllint (for YAML)
if ! command -v yamllint &> /dev/null; then
    echo "📦 Installing yamllint..."
    if command -v apt-get &> /dev/null; then
        sudo apt-get update && sudo apt-get install -y yamllint
    elif command -v brew &> /dev/null; then
        brew install yamllint
    else
        pip install yamllint
    fi
fi

# Make the scripts executable
chmod +x .githooks/pre-commit

echo "${GREEN}✅ Setup completed!${NC}"
echo "💡 For more information, see DEVELOPMENT.md"