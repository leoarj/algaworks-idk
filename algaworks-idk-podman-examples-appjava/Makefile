# Makefile para agrupar e facilitar a chamada de comandos de gerenciamento dos containers via Podman Compose

# Arquivos Compose
DEV_COMPOSE_FILE = docker-compose-dev.yml

# Arquivos de variáveis de ambiente
DEV_ENV_FILE ?= .env.dev

# Alvo padrão
.DEFAULT_GOAL := help

## ========================
## Ambiente de Desenvolvimento
## ========================

up-dev:
	podman compose --env-file $(DEV_ENV_FILE) -f $(DEV_COMPOSE_FILE) up --build

down-dev:
	podman compose --env-file $(DEV_ENV_FILE) -f $(DEV_COMPOSE_FILE) down

logs-dev:
	podman compose --env-file $(DEV_ENV_FILE) -f $(DEV_COMPOSE_FILE) logs -f

ps-dev:
	podman compose --env-file $(DEV_ENV_FILE) -f $(DEV_COMPOSE_FILE) ps

## ========================
## Utilitário
## ========================

help:
	@echo ""
	@echo "  Desenvolvimento:"
	@echo "    make up-dev      -> Sobe containers de desenvolvimento"
	@echo "    make down-dev    -> Para containers de desenvolvimento"
	@echo "    make logs-dev    -> Segue os logs de desenvolvimento"
	@echo "    make ps-dev      -> Lista containers de desenvolvimento"
	@echo "    make reset-dev   -> Derruba containers, remove volumes e sobe novamente"
	@echo ""
	@echo "Obs: Edite $(DEV_ENV_FILE) para trocar as variáveis de ambiente."
