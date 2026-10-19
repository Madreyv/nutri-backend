# Deploy no Render

Este backend pode ser criado no Render usando o `render.yaml` deste repositório. O blueprint cria uma Web Service Docker gratuita e pede as variáveis da base de dados e do domínio do frontend.

## Preparar o PostgreSQL hospedado

Crie primeiro uma base PostgreSQL externa, por exemplo no Neon. Não use o PostgreSQL gratuito do Render para dados que pretende manter: a instância gratuita expira após 30 dias.

Copie os dados locais de `nutrilab` para a nova base, incluindo o esquema `taco_import`. O backend espera as tabelas `alimento`, `categoria`, `grupo` e `alimento_grupo` nesse esquema. Use as credenciais JDBC TLS fornecidas pelo serviço e mantenha a origem local como cópia.

## Criar o serviço

1. Envie este projeto para um repositório GitHub.
2. No Render, escolha **New > Blueprint** e ligue o repositório.
3. Ao criar o serviço, preencha:
   - `DB_URL`: `jdbc:postgresql://<host>/<database>?sslmode=require`
   - `DB_USERNAME`: utilizador do PostgreSQL hospedado
   - `DB_PASSWORD`: senha do PostgreSQL hospedado
   - `CORS_ALLOWED_ORIGINS`: domínio HTTPS do site frontend, sem barra final
4. O health check é `GET /api/categorias`.

As credenciais devem ser inseridas nos Environment Variables do Render, nunca num ficheiro versionado. `spring.jpa.hibernate.ddl-auto=validate` permanece ativo: a aplicação exige que o esquema esteja criado antes de iniciar e não altera tabelas.

## Limitações do plano gratuito

O serviço pode suspender-se após inatividade e demorar a responder ao primeiro pedido. O frontend deverá apontar `VITE_API_BASE_URL` para a URL pública deste serviço seguida de `/api`.
