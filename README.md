# ToDoTribe
<p>Desenvolvimento moderno de um projeto estilo <b>To Do List</b> para Android com o uso do Hilt, Coroutines, LiveData, Jetpack (Room, ViewModel) e Material 3 Design, baseado na arquitetura MVVM.</p>

## Sobre o Projeto:

- Nível mínimo do SDK 26
- Kotlin
  - Ciclo de Vida: Observa os ciclos de vida do Android e lida com os estados da interface do usuário nas mudanças do ciclo de vida.
  - ViewModel: Gerencia os dados relacionados à interface do usuário e está ciente do ciclo de vida. Permite que os dados sobrevivam a mudanças de configuração, como rotações de tela.
  - DataBinding: Vincula os componentes da interface do usuário em seus layouts às fontes de dados em seu aplicativo usando um formato declarativo em vez de programaticamente.
  - Room: Constrói o banco de dados fornecendo uma camada de abstração sobre o SQLite para permitir acesso ao banco de dados de forma fluida.
  - Gráficos do Componente de Navegação - Para usar uma arquitetura de atividade única com vários fragmentos.
  - Hilt: Para injeção de dependência.
  - Coroutines Para programação assíncrona.
- Arquitetura
  - Arquitetura MVVM (View - DataBinding - ViewModel - Model)
  - Padrão de Repositório
- Componentes de Material: Componentes de design de material para criar animações de ondulação e CardView.
- Seletor de Cores: Simples seletor de cores para Android com roda de cores e barra de luminosidade.
