#Projeto Blog Tangerino
Desenvolvi a parte do back-end com mais assertividade, porém a parte do front end não fiz completamente devido a minha baixa experiencia com angular 2+.

O modelo de dados ficou da seguinte forma:


#### Back-end
Para executar os testes, abrir a pasta ./back-end/blog/ no terminal e executar o comando abaixo:

mvn surefire:test

---

Para executar a aplicação, é necessário ter instalado na máquina o banco de dados Postgress e o banco de dados "blog" criado.

Passo 1 - Executar na pasta ./back-end/blog o comnado "mvn package"
Passo 2 - Executar o comando "java -jar ./target/blog-0.0.1-SNAPSHOT.jar"

Para testar os endpoints, disponibilizei uma collection com as chamadas na raiz do projeto. O nome do arquivo é "blog_tangerino.postman_collection.json"

### Front-end

Para executar a aplicação, abrir a pasta ./front-end/blog-tangerino-client/src/app no terminal e executar o comando "ng serve"
