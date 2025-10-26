# Containerização de app com ***Podman***

Simples app Java para  teste de containerização com Podman e compatibilidade com Docker.

## Comandos utilizados para build e escaneamento da imagem

Construir e taguear imagem para análise de CVEs.

- Comando para build da imagem:
    ```console
    podman build -f maven-multistage.Dockerfile -t java-app . 
    ```
    >Poderia ser também `maven-multistage.Dockerfile.Containerfile`

- Comando para Escaneamento com ***Trivy***:
    ```console
    trivy image java-app
    ```

## Escaneamento de CVE

[**Clique aqui para visualizar o relatório completo (trivy-scan-report.html).**](trivy-scan-report.html)

### Vunerabilidades encontradas

- '0': Clean (no security findings detected)

## Correções de vulnerabilidades

Devido não ser localizda nenhuma vulnerabilidade na imagem não foi necessário realizar mudança de versão de dependências ou remoção.

## Execução com ***Podman***

- Executando com `podman run`
    ```console
    podman run -d -p 8080:8080 --rm --name java-app java-app
    ```

- Testando endpoint
    ```console
    curl http://localhost:8080/ping
    ```

## Realizando a assinatura da imagem com ***Cosign*** com *key-based signing*

Para assinar a imagem, primeiro é necessário aplicar uma tag para apontar para nosso repositório no Registry Docker Hub
e em seguida realizar o push da imagem.

- Comando utilizado para aplicar a tag:
    ```console
    podman tag java-app docker.io/leoarj/java-app:podman
    ```

- Comando para realizar o push:
    ```console
    podman push docker.io/leoarj/java-app:podman
    ```

- Comando utilizado parar gerar par de chaves:
    ```console
    cosign generate-key-pair
    ````

- Comando utilizado para assinar a imagem (Usa a chave privada):
    ```console
    cosign sign --key cosign/cosign.key docker.io/leoarj/java-app:podman
    ```

- Comando utilizado para verificação da assinatura da imagem (Usa a chave pública):
    ```console
    cosign verify --key cosign/cosign.pub index.docker.io/leoarj/java-app:podman | jq
    ```
  >Obs.: Com utilitário *jq* para formatação da saída em json.

- Resultado da verificação da assinatura:
    ```txt
    Verification for index.docker.io/leoarj/java-app:podman --
  The following checks were performed on each of these signatures:
  - The cosign claims were validated
  - Existence of the claims in the transparency log was verified offline
  - The signatures were verified against the specified public key
    ```
    ```json
    [
        {
            "critical": {
            "identity": {
                "docker-reference": "index.docker.io/leoarj/java-app"
            },
            "image": {
                "docker-manifest-digest": "sha256:c4fb0a3f578ee53124a883b0ccf9e1436930b8f8a470629a3052852b870b2801"
            },
            "type": "cosign container image signature"
            },
            "optional": {
            "Bundle": {
                "SignedEntryTimestamp": "MEQCIGKGH/ejc18qxUygKX9bpaKtycnkzxlu2ubzLBQxczpaAiBEEuOtzQ95oAWEz0XdDYVo0JdV5WVyF7S0oEXEDj0xfQ==",
                "Payload": {
                "body": "eyJhcGlWZXJzaW9uIjoiMC4wLjEiLCJraW5kIjoiaGFzaGVkcmVrb3JkIiwic3BlYyI6eyJkYXRhIjp7Imhhc2giOnsiYWxnb3JpdGhtIjoic2hhMjU2IiwidmFsdWUiOiJhYmM0ZTVkNDA3YmUzNTdjZThkMzVjZGY0Y2UxY2RjN2RhYTVmNTc4Yzg4ZTc3ZDc4ODAxMjc4ZGNlOTljNTAwIn19LCJzaWduYXR1cmUiOnsiY29udGVudCI6Ik1FVUNJUUNLazNrYWFNRnZBaEJPbWZna3AzRkFYMWQ1ZlV1U2FSekY0V2dUZjZ6ZG9nSWdWSzdUN2VweWN1Z1V6dkFBdTJGbjE3d1pyR0s1eTR4VTQ4WkYzaWhkUUcwPSIsInB1YmxpY0tleSI6eyJjb250ZW50IjoiTFMwdExTMUNSVWRKVGlCUVZVSk1TVU1nUzBWWkxTMHRMUzBLVFVacmQwVjNXVWhMYjFwSmVtb3dRMEZSV1VsTGIxcEplbW93UkVGUlkwUlJaMEZGWnpWcGFIWkpWVkEyUzJKSWR6UTRRMk5rYjJOTWVUVlJOV3BITUFwRWIxbG1jRzB5ZEdOUE56bG9kWHBTVVRGbEsxZEVhM05VUm01UGJDOWpTWGMxUzB4cVdHZHFhRVZFVDFaTk5XSjFTUzlLZW5wUFNrTlJQVDBLTFMwdExTMUZUa1FnVUZWQ1RFbERJRXRGV1MwdExTMHRDZz09In19fX0=",
                "integratedTime": 1760221408,
                "logIndex": 601070756,
                "logID": "c0d23d6ad406973f9559f3ba2d1ca01f84147d8ffc5b8445c224f98b9591801d"
                }
            }
            }
        }
    ]
    ```
## Referências
  - https://www.redhat.com/en/blog/rootless-containers-podman
  - https://www.redhat.com/en/blog/write-your-first-containerfile-podman