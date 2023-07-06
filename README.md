Busca de CEP
# Descrição
O Busca de CEP é um aplicativo desenvolvido em Java que permite aos usuários buscar endereços por meio do CEP. Ele utiliza a tecnologia do banco de dados Room para armazenar o histórico de buscas, integra a API ViaCEP para obter os dados de endereço e utiliza o RecyclerView, Models e Adapters para criar uma interface intuitiva e interativa.

# Funcionalidades
# Busca de Endereço
- O aplicativo permite aos usuários digitar um CEP na tela de adicionar e realizar uma busca. Ao realizar a busca, o aplicativo consome a API ViaCEP e exibe o endereço completo correspondente ao CEP, incluindo rua, bairro, cidade e estado.

# Histórico de Busca
- O aplicativo mantém um histórico das buscas realizadas pelos usuários. Mesmo após excluir o aplicativo, as informações do histórico são preservadas. O histórico é exibido na tela inicial, permitindo aos usuários revisitar as buscas anteriores.

# Tecnologias Utilizadas
- Database Room: O Room é uma biblioteca do Android que fornece uma camada de abstração sobre o SQLite, permitindo a criação e gerenciamento de um banco de dados local no aplicativo. Ele é utilizado para armazenar e recuperar as informações do histórico de buscas.

# API ViaCEP: 
- A API ViaCEP é uma interface de programação de aplicativos que permite acessar informações de endereços com base no CEP. O aplicativo consome essa API para obter os dados de endereço correspondentes a um CEP digitado pelo usuário.

# RecyclerView: 
- O RecyclerView é um componente do Android que permite exibir listas de itens de forma eficiente e flexível. Ele é utilizado no aplicativo para exibir tanto a lista de buscas anteriores quanto os resultados da busca atual.

# Models e Adapters:
- O aplicativo utiliza modelos (Models) para representar os dados de endereço e as buscas anteriores. Adapters são usados para fazer a ligação entre esses modelos e as visualizações exibidas na interface do usuário.
