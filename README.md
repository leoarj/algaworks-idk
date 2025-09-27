# AlgaWorks - Imersão Docker (algaworks-idk)

🇧🇷
Repositório para registro de estudos e exercícios de imersão em DevOps (Docker), baseado no curso "Imersão Docker" da AlgaWorks.

🇺🇸
*Repository for registry of study and exercises of immersion in DevOps (Docker), based on AlgaWorks's course "Docker Immersion".*

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

## Índice
- [Sobre](#sobre)
- [Tópicos explorados](#tópicos-explorados)
- [Autor](#autor)
- [Agradecimentos](#agradecimentos)
- [Licença](#licença)

## Sobre
Reune exemplos e exercícios que realizei durante o curso "Imersão Docker", onde foi explorado DevOps com o uso de Docker, desde a instalação da engine,
criação de containers a partir de imagens prontas, construção de imagens próprias, gerenciamento de containers, images, volumes, e networks,
*Docker DinD (Docker-Inside-Docker)*, deploys, ambiente de desenvolvimento com *DevContainer* entre outros.

### Objetivos
O objetivo é deixar registrado para consultas futuras e caso seja útil, ajudar também outras pessoas que estejam estudando algo parecido.

### Observações
- Listagem de tópicos está simplificada, de acordo com os desafios que realizei, a listagem dentro do treinamento é muito maior e mais detalhada.

## Tópicos explorados
- Introdução
    - Conceitos de containerização
    - Instalação da engine
    - Criação de containers a partir de imagens prontas
    - Entendendo sobre Docker Images
    - Arquivo Dockerfile e criando imagens a partir dele
    - Entendendo sobre Docker Hub
        - Exploração, pull, push e etiquetas (tag) de imagens
- Gerenciamento de containers
- Criação de imagens
- Gerenciamento de Docker Volumes
- Gerenciamento de Docker Networks
- Gerenciamento com Docker Compose
- Deploy de aplicações containerizadas no ELB (Elastic Bean Stalk) da AWS
- História dos modelos de deploy até a chegada do Docker
    - Arquitetura do Docker
    - Isolamento de Containers
    - Filesystem
    - DinD (Docker-inside-Docker)
    - API do Docker
    - Docker SDK
    - Docker Extensions
- Ambiente de Desenvolvimento com Docker
    - Container Tools no VS Code
    - Debug remoto com JDWP (Java Debug Wire Protocol)
    - DevContainer no VS Code
- Segurança com Docker
- Outras engines (Buildah e Podman)
- outros tópicos presentes nos projetos...

- Referências
    - https://docs.docker.com/reference/dockerfile/
    - https://docs.docker.com/reference/cli/docker/container/stats/#format
    - https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/envvars002.html
    - https://semver.org/lang/pt-BR/
    - https://github.com/jwilder/dockerize
    - https://docs.docker.com/compose/how-tos/multiple-compose-files/extends/
    - https://formulae.brew.sh/formula/aws-elasticbeanstalk#default
    - https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/using-features.managing.db.html
    - https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/using-features.managing.db.html#environments-cfg-rds-create
    - https://meldmerge.org/
    - https://docs.docker.com/reference/api/engine/version/v1.51/#tag/Container
    - https://docs.docker.com/reference/api/engine/
    - https://docs.docker.com/reference/api/engine/version-history/
    - https://testcontainers.com/
    - https://docs.docker.com/reference/api/engine/sdk/
    - https://github.com/docker-java/docker-java
    - https://docs.spring.io/spring-boot/reference/actuator/enabling.html#actuator.enabling
    - https://containers.dev/
    - https://containers.dev/guide/dockerfile
    - https://code.visualstudio.com/remote/advancedcontainers/environment-variables
    
## Autor
Leandro Araújo, desenvolvedor Java, com foco em backend.<br>
Busco me aperfeiçoar por meio de cursos e projetos como este, a fim de crescer profissionalmente e humanamente por meio da colaboração.<br><br>
Caso se sinta à vontade, pode entrar em contato:
- https://www.linkedin.com/in/leandroaraujo-dev/

## Agradecimentos
- Instrutor: Highlander Dantas (https://www.linkedin.com/in/highlander-dantas-16a61512a/)
- https://www.algaworks.com/
- https://blog.algaworks.com/

## Licença
Este projeto é licenciado sob a [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html).

Para mais detalhes, consulte o arquivo [LICENSE](./LICENSE).

[Voltar ao início](#algaworks---imersão-docker-algaworks-idk)