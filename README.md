# Spring Batch

## Sobre o framework

Uma estrutura de lote leve e abrangente, projetada para permitir o desenvolvimento de aplicativos robustos em lote, vitais para as operações diárias dos sistemas corporativos.

O *Spring Batch* fornece funções reutilizáveis, essenciais no processamento de grandes volumes de registros, incluindo registro/rastreamento, gerenciamento de transações, estatísticas de processamento de trabalhos, reinício de trabalhos, pular e gerenciamento de recursos. Ele também fornece serviços e recursos técnicos mais avançados que permitirão tarefas em lote de alto volume e alto desempenho por meio de técnicas de otimização e particionamento. Trabalhos em lote de alto volume, simples e complexos, podem alavancar a estrutura de uma maneira altamente escalável para processar volumes significativos de informações. Disponível em: https://spring.io/projects/spring-batch

## Sobre o código

### Acrescentar leitura de novos tipos de dados

Para acrescentar a leitura de novos tipos de dados teria que criar:

* A *Entity* no pacote modelo;
* Uma condição em *ObjectFielSetMapper* para leitura dos dados;
* Um método na classe *WriterArchive*, informando o que será salvo na saída;
* A chamada do método criado na classe *WriterArchive*, dentro da classe *ConsoleItemWriter*.

#### Observações:
A aplicação só lê arquivos no formato .dat. Também não vai ler registros com o mesmo ID, pois ele entende que já foram inseridos.

Para enviar vários arquivos a serem analisados, pode-se utilizar o *endpoint* http://localhost:8080 com o parâmetro *uploadingFiles*.