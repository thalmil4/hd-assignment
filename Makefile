.PHONY: help build clean test test-valid test-invalid test-security test-regression test-firefox test-webkit shell reports screenshots verify prune

# Docker image and container names
IMAGE_NAME := hd-test-automation
CONTAINER_NAME := hd-test-runner

# Directories
WORK_DIR := $(shell pwd)
TARGET_DIR := $(WORK_DIR)/target
SCREENSHOTS_DIR := $(WORK_DIR)/screenshots

# Colors
GREEN := \033[0;32m
YELLOW := \033[0;33m
RED := \033[0;31m
NC := \033[0m

help: ## Show this help message
	@echo "========================================"
	@echo "HD Assignment - Docker Test Automation"
	@echo "========================================"
	@echo ""
	@echo "Available targets:"
	@echo ""
	@echo "Examples:"
	@echo "  make build              # Build the Docker image"
	@echo "  make test               # Run all tests"
	@echo "  make test ARGS='-Dtest=ValidTestRunner'  # Run with custom args"
	@echo ""

build: ## Rebuild Docker image without cache
	@echo "$(GREEN)Rebuilding Docker image (no cache)...$(NC)"
	@docker build --no-cache -t $(IMAGE_NAME) .
	@echo "$(GREEN)Rebuild completed!$(NC)"

test: build ## Run all tests (use ARGS= for custom arguments)
	@echo "$(GREEN)Running tests: mvn clean test $(ARGS)$(NC)"
	@mkdir -p $(HOME)/.m2
	@docker run --rm \
		--name $(CONTAINER_NAME) \
		-v $(TARGET_DIR):/app/target \
		-v $(SCREENSHOTS_DIR):/app/screenshots \
		-v $(HOME)/.m2:/.m2 \
		-e MAVEN_CONFIG=/.m2 \
		-e BROWSER=$(or $(BROWSER),chromium) \
		-e HEADLESS=$(or $(HEADLESS),true) \
		$(IMAGE_NAME) \
		sh -c "mvn test $(ARGS)"