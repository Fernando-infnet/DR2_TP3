# Exercício 1

Essa abordagem evita problemas porque os dados não são os mesmos durante diferentes alterações, na verdade um novo objeto é criado com valores atualizados, que fazem a persistência do valor ser algo que tem integridade, depois se necessário, por exemplo um banco, poderia checar se a alteração PODE ser feita e se DEVE ser feita, aplicando restrições, checando permissões e você sempre possui o valor original que você ainda pode retornar dependendo da execução. E transações bancárias por exemplo não são colocadas em filas, cada pessoa faz uma transação ou extração de dinheiro simultâneamente, então o código tem que ser capaz de manter os dados válidos até o final da execução de um outro usuário, ou para o mesmo usuário se este estiver executando mais de uma função por vez.

Motivações e benefícios:

Segurança e integridade dos dados: evita alterações inesperadas em objetos compartilhados.

Facilidade de teste: cada objeto é previsível, sem efeitos colaterais.

Concorrência: múltiplos usuários podem executar operações simultâneas sem risco de sobrescrever dados.

Trade-offs:

Uso de memória: criação de novos objetos pode aumentar consumo de memória em sistemas muito grandes.

Complexidade inicial: requer mudança de mentalidade em relação à atualização direta de objetos.

# Exercício 2 

public void addTask(Task task) {
    tasks.add(task); 
}

Esse trecho do objeto Sprint é perigoso porque chama as próprias variáveis do objeto que adiciona mais um task ao tasks que estão definidos no objeto.

public class Sprint { 
    private final String name; 
    private final LocalDate startDate; 
    private final LocalDate endDate; 
    private final List<Task> tasks = new ArrayList<>();
}

Portanto uma forma melhor de adicionar tasks ao sprint seria se este pudesse criar um objeto separado da execução e retornar a cópia com a adição do task adicionado. 

Sprint updatedSprint = sprint.withTaskAdded(task);

Os benefícios incluem o que foi discutido no exemplo anterior, que múltiplas threads não precisariam de uma sincronização entre as execuções e preocupação com a integridade de aplicação considerando que o usuário poderia assegurar que o objeto não mudou durante a execução.

Motivações e benefícios:

Imutabilidade protege o estado original, garantindo previsibilidade e segurança em operações simultâneas.

Facilidade de auditoria: é possível comparar versões anteriores do objeto facilmente.

Manutenção: reduz o risco de efeitos colaterais inesperados.

Trade-offs:

Complexidade de código: exige métodos adicionais (withTaskAdded, withTaskRemoved) e cópia de listas.

Desempenho: cópias de listas podem ser custosas se houver muitas tarefas, mas geralmente o benefício de segurança supera o custo.

# Exercício 3 

RESULTADOS DA EXECUÇÃO MAIN:

Projeto: Sistema de Gestão de Projetos
  Sprint: Planejamento Inicial
    Tarefa: Title: Implementar Tela de Login | Description: Criar o endpoint | Status: TODO | Assignee: Fernando
    Tarefa: Title: Escrever Testes | Description: Escrever testes | Status: TODO | Assignee: Ana

Itens de Estoque:
  Item[sku=MOUSEC9, quantity=3, price=70.00]
  Item[sku=KEYBOARD12, quantity=2, price=60.00]

Após atualizar a quantidade (Exemplo Imutabilidade):
  Original (Mouse): Item[sku=MOUSEC9, quantity=3, price=70.00]
  Atualizado (Mouse): Item[sku=MOUSEC9, quantity=5, price=70.00]

Tarefas do Sprint após remover uma tarefa (Exemplo Imutabilidade):
  Tarefas do Sprint Original:
    Title: Implementar Tela de Login | Description: Criar o endpoint | Status: TODO | Assignee: Fernando
    Title: Escrever Testes | Description: Escrever testes | Status: TODO | Assignee: Ana
  Tarefas do Novo Sprint:
    Title: Escrever Testes | Description: Escrever testes | Status: TODO | Assignee: Ana

Motivações e benefícios:

Controle de versões: objetos originais permanecem intactos.

Segurança em ambientes concorrentes: várias threads podem ler objetos sem sincronização.

Facilidade de testes: cada operação gera um novo estado previsível.

Trade-offs:

Mais código para cópias e métodos de atualização.

Possível uso maior de memória se muitos objetos forem criados rapidamente.

# Exercício 4

private final UUID id;              
private final String description;     
private final BigDecimal amount;     
private final int quantity;         
private final LocalDateTime createdAt;
private final LocalDateTime updatedAt; 
private final HistoryStatus status;  

UUID é padrão de indústria para garantir um identificador global
String é o que pode ser usado para gerenciar descrições que são unidades de TEXTO, que se encaixam nesse padrão de serem descritivas, tamanho entre pequeno e grande e não necessitam de constante manipulação edição dos dados.
BigDecimal de primeira vista você pode pensar que int, double, float pode servir no seu lugar considerando que ambos representam NÚMEROS porém, em cálculos de extração, transações, soma, as vezes é possível que ocorra uns problemas explicitamente no fator decimal, portanto esse tipo, que não tem esse problema, é geralmente utilizado.
int assim como qualquer tipo numérico pode ser utilizado aqui porém ele é o mais simples para uma tarefa simples como quantidade, que não existe em casas decimais por exemplo, ou negativo, ou qualquer coisa que precise de um espaço enorme.
createdAt unidade de tempo, que também poderia ser Date, porém dado a estrutura de precisar dados de criado e atualizado geralmente o padrão é createdAt e updatedAt, para atualizações o posterior.
status é um valor que você define quais tipos são possíveis no objeto, útil quando entradas diversas não são necessárias e apenas suas definidas são possíveis.

Trade-offs:

BigDecimal é mais pesado que tipos primitivos, mas necessário para precisão financeira.

Imutabilidade exige criação de novos objetos para atualizações, aumentando memória temporariamente.

Enums limitam flexibilidade, mas aumentam segurança de dados e consistência.

# Exercício 5

1. Papéis das classes

Domínio: representam conceitos centrais e mantêm dados coesos. Ex.: User, Task, Sprint, Project, Item, Record. Cada classe possui métodos que retornam novos objetos para preservar imutabilidade.

Serviço: executam lógica de negócio, interagindo com entidades, sem lidar diretamente com persistência. Ex.: UserService, ProjectService, RecordService.

Repositório: isolam persistência, implementando métodos básicos via interface genérica Repository<T> (save, findById, delete). Serviços recebem repositórios via injeção de dependência (construtor), permitindo troca de implementação sem impactar o serviço.

2. Interfaces

Definem contratos claros (Repository<T>, UserServiceInterface) e permitem mock para testes, promovendo baixo acoplamento.

3. Herança vs Composição

Herança: útil para entidades semelhantes (FinancialEntity para Record, Invoice, Payment), mas aumenta acoplamento.

Composição: preferida quando se quer reutilizar funcionalidades sem criar dependência rígida (ex.: Record tem Money ao invés de herdar).

4. Agrupamento e coesão

Sprint agrupa Tasks; Project agrupa Sprints; Record agrupa atributos e operações financeiras. Mantém atributos e comportamentos relacionados juntos, evitando código espalhado e confuso.

5. Benefícios esperados e trade-offs

Imutabilidade: reduz efeitos colaterais, facilita concorrência.

Baixo acoplamento: serviços desacoplados de implementações concretas.

Responsabilidade única: clareza de papel de cada classe.

Contratos claros: interfaces definem regras consistentes.

Composição sobre herança: evita sobrecarga e dependência excessiva.

Tipos fortes: BigDecimal, LocalDateTime, enum aumentam legibilidade e precisão.