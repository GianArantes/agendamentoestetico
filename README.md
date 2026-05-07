# API Agendamento Estético
Documentação da API de agendamentos

## Version: 1.0

### Servers

| URL | Description |
| --- | ----------- |
| http://localhost:8080 | Generated server url |

### Available authorizations
#### bearerAuth (HTTP, bearer)
Bearer format: JWT

---

### [GET] /procedimentos/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Procedimento](#procedimento-schema)<br> |

### [PUT] /procedimentos/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  No | **application/json**: [ProcedimentoDTO](#procedimentodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Procedimento](#procedimento-schema)<br> |

### [DELETE] /procedimentos/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /procedimentos
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| dto | query |  | Yes | [ProcedimentoDTO](#procedimentodto-schema) |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Procedimento](#procedimento-schema)<br> |

### [GET] /procedimentos/listar
#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [Procedimento](#procedimento-schema) ]<br> |

---

### [GET] /cliente/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Cliente](#cliente-schema)<br> |

### [PUT] /cliente/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [ClienteDTO](#clientedto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Cliente](#cliente-schema)<br> |

### [DELETE] /cliente/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /cliente
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| dto | query |  | Yes | [ClienteDTO](#clientedto-schema) |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Cliente](#cliente-schema)<br> |

### [GET] /cliente/listar
#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [Cliente](#cliente-schema) ]<br> |

---

### [GET] /auth/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Funcionario](#funcionario-schema)<br> |

### [PUT] /auth/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [FuncionarioModeradoDTO](#funcionariomoderadodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Funcionario](#funcionario-schema)<br> |

### [DELETE] /auth/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /auth/solicitar-recuperacao
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: string<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: string<br> |

### [POST] /auth/registro
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [FuncionarioDTO](#funcionariodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Funcionario](#funcionario-schema)<br> |

### [POST] /auth/recuperar-senha
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| token | query |  | Yes | string |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [RecuperaSenhaDTO](#recuperasenhadto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: string<br> |

### [POST] /auth/logout
#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /auth/login
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [AuthDTO](#authdto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [LoginResponseDTO](#loginresponsedto-schema)<br> |

### [POST] /auth/altera-senha
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [AtualizaSenhaDTO](#atualizasenhadto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [FuncionarioResumoDTO](#funcionarioresumodto-schema)<br> |

### [GET] /auth/listar-funcionarios
#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [FuncionarioListagemDTO](#funcionariolistagemdto-schema) ]<br> |

---

### [GET] /agenda-trabalho/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaTrabalhoDTOResponse](#agendatrabalhodtoresponse-schema)<br> |

### [PUT] /agenda-trabalho/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [AgendaTrabalhoDTO](#agendatrabalhodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaTrabalhoDTOResponse](#agendatrabalhodtoresponse-schema)<br> |

### [DELETE] /agenda-trabalho/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /agenda-trabalho
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [AgendaTrabalhoDTO](#agendatrabalhodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaTrabalhoDTOResponse](#agendatrabalhodtoresponse-schema)<br> |

### [GET] /agenda-trabalho/listar/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [AgendaTrabalhoDTOResponse](#agendatrabalhodtoresponse-schema) ]<br> |

---

### [GET] /agenda-bloqueio/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaBloqueio](#agendabloqueio-schema)<br> |

### [PUT] /agenda-bloqueio/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Request Body

| Required | Schema |
| -------- | ------ |
|  No | **application/json**: [AgendaBloqueioDTO](#agendabloqueiodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaBloqueio](#agendabloqueio-schema)<br> |

### [DELETE] /agenda-bloqueio/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

### [POST] /agenda-bloqueio
#### Request Body

| Required | Schema |
| -------- | ------ |
|  No | **application/json**: [AgendaBloqueioDTO](#agendabloqueiodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [AgendaBloqueio](#agendabloqueio-schema)<br> |

### [GET] /agenda-bloqueio/listar
#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [AgendaBloqueio](#agendabloqueio-schema) ]<br> |

---

### [POST] /agendamento
#### Request Body

| Required | Schema |
| -------- | ------ |
|  Yes | **application/json**: [AgendamentoDTO](#agendamentodto-schema)<br> |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [Agendamento](#agendamento-schema)<br> |

### [GET] /agendamento/ocupados
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| data | query |  | Yes | date |
| funcionarioId | query |  | Yes | long |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ [IntervaloOcupadoDTO](#intervaloocupadodto-schema) ]<br> |

### [GET] /agendamento/horarios-disponiveis
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| dto | query |  | Yes | [HorarioLivreDTO](#horariolivredto-schema) |

#### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | ***/***: [ string ]<br> |

### [DELETE] /agendamento/{id}
#### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ------ |
| id | path |  | Yes | long |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |

---
### Schemas

#### ProcedimentoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| nome | string |  | No |
| descricao | string |  | No |
| preco | number |  | No |
| duracao | integer |  | No |

#### Procedimento Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| nome | string |  | No |
| descricao | string |  | No |
| preco | number |  | No |
| duracao | string |  | No |

#### ClienteDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| nome | string |  | No |
| celular | string |  | No |
| email | string |  | No |
| dataNascimento | date |  | No |

#### Cliente Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| nome | string |  | No |
| celular | string |  | No |
| email | string |  | No |
| status | string, <br>**Available values:** "ATIVO", "INATIVO", "BLOLOQUEADO" | *Enum:* `"ATIVO"`, `"INATIVO"`, `"BLOLOQUEADO"` | No |
| dataNascimento | date |  | No |
| agendamentoToken | string |  | No |
| agendamentoTokenExpiration | dateTime |  | No |

#### FuncionarioModeradoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| nome | string |  | Yes |
| email | string |  | Yes |
| celular | string |  | Yes |
| novaSenha | string |  | No |
| role | string, <br>**Available values:** "ROLE_ADMIN", "ROLE_GERENTE", "ROLE_FUNCIONARIO" | *Enum:* `"ROLE_ADMIN"`, `"ROLE_GERENTE"`, `"ROLE_FUNCIONARIO"` | No |
| status | string, <br>**Available values:** "ATIVO", "INATIVO", "BLOLOQUEADO" | *Enum:* `"ATIVO"`, `"INATIVO"`, `"BLOLOQUEADO"` | No |

#### Funcionario Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| nome | string |  | No |
| celular | string |  | No |
| email | string |  | No |
| status | string, <br>**Available values:** "ATIVO", "INATIVO", "BLOLOQUEADO" | *Enum:* `"ATIVO"`, `"INATIVO"`, `"BLOLOQUEADO"` | No |
| senha | string |  | No |
| role | string, <br>**Available values:** "ROLE_ADMIN", "ROLE_GERENTE", "ROLE_FUNCIONARIO" | *Enum:* `"ROLE_ADMIN"`, `"ROLE_GERENTE"`, `"ROLE_FUNCIONARIO"` | No |
| resetSenhaToken | string |  | No |
| senhaTokenExpiration | dateTime |  | No |
| enabled | boolean |  | No |
| password | string |  | No |
| username | string |  | No |
| credentialsNonExpired | boolean |  | No |
| accountNonLocked | boolean |  | No |
| accountNonExpired | boolean |  | No |
| authorities | [ [GrantedAuthority](#grantedauthority-schema) ] |  | No |

#### GrantedAuthority Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| authority | string |  | No |

#### AgendaTrabalhoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| diaDaSemana | string, <br>**Available values:** "SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA", "SABADO", "DOMINGO" | *Enum:* `"SEGUNDA"`, `"TERCA"`, `"QUARTA"`, `"QUINTA"`, `"SEXTA"`, `"SABADO"`, `"DOMINGO"` | No |
| agendaInicio | string |  | No |
| agendaFim | string |  | No |
| almocoInicio | string |  | No |
| almocoFim | string |  | No |
| funcionarioId | long |  | No |

#### AgendaTrabalhoDTOResponse Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| diaDaSemana | string, <br>**Available values:** "SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA", "SABADO", "DOMINGO" | *Enum:* `"SEGUNDA"`, `"TERCA"`, `"QUARTA"`, `"QUINTA"`, `"SEXTA"`, `"SABADO"`, `"DOMINGO"` | No |
| agendaInicio | string |  | No |
| agendaFim | string |  | No |
| almocoInicio | string |  | No |
| almocoFim | string |  | No |
| funcionarioId | long |  | No |

#### AgendaBloqueioDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| inicioBloqueio | dateTime |  | No |
| fimBloqueio | dateTime |  | No |
| motivo | string |  | No |

#### AgendaBloqueio Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| inicioBloqueio | dateTime |  | No |
| fimBloqueio | dateTime |  | No |
| motivo | string |  | No |
| funcionario | [Funcionario](#funcionario-schema) |  | No |

#### FuncionarioDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| nome | string |  | Yes |
| email | string |  | Yes |
| celular | string |  | Yes |
| senha | string |  | Yes |

#### RecuperaSenhaDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| novaSenha | string |  | Yes |
| confirmaNovaSenha | string |  | Yes |

#### AuthDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| email | string |  | Yes |
| senha | string |  | Yes |

#### LoginResponseDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| token | string |  | No |

#### AtualizaSenhaDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| senhaAntiga | string |  | Yes |
| novaSenha | string |  | Yes |
| confirmaNovaSenha | string |  | Yes |

#### FuncionarioResumoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| nome | string |  | No |
| email | string |  | No |

#### AgendamentoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| clienteId | long |  | No |
| funcionarioId | long |  | No |
| procedimentoId | long |  | No |
| inicio | dateTime |  | No |

#### Agendamento Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| cliente | [Cliente](#cliente-schema) |  | No |
| funcionario | [Funcionario](#funcionario-schema) |  | No |
| procedimento | [Procedimento](#procedimento-schema) |  | No |
| inicioProcedimento | dateTime |  | No |
| fimProcedimento | dateTime |  | No |

#### FuncionarioListagemDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| nome | string |  | No |
| email | string |  | No |
| celular | string |  | No |
| role | string, <br>**Available values:** "ROLE_ADMIN", "ROLE_GERENTE", "ROLE_FUNCIONARIO" | *Enum:* `"ROLE_ADMIN"`, `"ROLE_GERENTE"`, `"ROLE_FUNCIONARIO"` | No |

#### IntervaloOcupadoDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| inicioBloqueio | string |  | No |
| fimBloqueio | string |  | No |
| motivo | string |  | No |

#### HorarioLivreDTO Schema

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| data | date |  | Yes |
| funcionarioId | long |  | Yes |
| procedimentoId | long |  | Yes |
