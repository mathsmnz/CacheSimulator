# CacheSimulator
[Link para o repositório](https://github.com/mathsmnz/CacheSimulator)

## Sobre o projeto
Este projeto contém os arquivos relevantes ao trabalho 2 de AOCII, o qual se trata do desenvolvimento de um programa para benchmark de caches.


## Autores
- Raíssa Nunes Coelho - 15102887
- Matheus S. Menezes - 19100956

## Estrutura de arquivos
### 1 — Pasta Util
- CliParser
  - Classe responsável por gerenciar a entrada de argumentos por linha de comando
- FileManager
  - Classe que contém os métodos responsáveis por lidar com os arquivos
- RuntimeData
  - Classe que contém variáveis relativas à execução do programa
- Util
  - Classe que contém métodos utilitários
- LogStream
  - Classe que contém uma PrintStream modificada para permitir escrita em terminal e arquivo de forma simultânea
- LogHandler
  - Classe responsável por gerenciar os Logs gerados pelo programa

### 2 — Pasta Memoria
- Conjunto
  - Classe que contém o conjunto e seus campos
- Cache
  - Classe que gerencia a cache

## 3 — Execução do programa
- Para a execução do programa se utiliza a seguinte linha de comando:
    cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> arquivo_de_entrada

- Os argumentos de execução são:
  - nsets: número de conjuntos na cache (número total de “linhas” ou “entradas” da cache);
  - bsize: tamanho do bloco em bytes;
  - assoc: grau de associatividade (número de vias ou blocos que cada conjunto possui);
  - substituição: política de substituição, que pode ser:
    - Random (R) 
    - FIFO (F) 
    - L (LRU);
  - flag_saida: flag que ativa o modo padrão de saída de dados
    - 0 - Saída em terminal, contendo todas as informações de execução.
    - 1 - Saída em terminal, contendo o estritamente necessário.
    - 2 - Saída em terminal, contendo extras sobre execução
    - 3 - Saída em terminal e arquivo, contendo extras sobre execução
    - 4 - Saída em terminal e arquivo, contendo todas as informações de execução.
  - arquivo_de_entrada: - arquivo com os endereços para acesso à cache em formato .bin.