{
	"info": {
		"_postman_id": "4533f17c-ea51-407c-949a-127ceff47ad1",
		"name": "Blog Tangerino",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Login",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": ""
				}
			},
			"response": []
		},
		{
			"name": "Criar Usuário",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjQxNTIsImV4cCI6MTYwMTI1MDU1Mn0.bNOqHZfFmSEQoUALLtvlmcVYZqM_ciBiTyYwIJrb5aA",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"login\":\"teste.barbara\",\n    \"senha\":\"123456\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "{{local}}/usuario",
					"host": [
						"{{local}}"
					],
					"path": [
						"usuario"
					]
				}
			},
			"response": []
		},
		{
			"name": "Criar Postagem",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjQzMTcsImV4cCI6MTYwMTI1MDcxN30.3YMoPd-7VZqGwminUZ3zZzjIC0rbeZFHVBUDEpjsSHU",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"titulo\": \"Titulo da minha primeira postagem Higor 1\",\n    \"texto\": \"Minha primeira postagem Higor 1\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "{{local}}/postagem",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem"
					]
				}
			},
			"response": []
		},
		{
			"name": "Buscar Postagens",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNTgwMzksImV4cCI6MTYwMTI0NDQzOX0.lG_J5pKpzGjmUvON_t-YkN-4smG8-wwXV-JT4iZnQuM",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "GET",
				"header": [],
				"url": {
					"raw": "{{local}}/postagem?size=10&page=0",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem"
					],
					"query": [
						{
							"key": "size",
							"value": "10"
						},
						{
							"key": "page",
							"value": "0"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Deletar postagem",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjQzNzEsImV4cCI6MTYwMTI1MDc3MX0.nOPgCXFbZsNtUehP40LsFjzj_ErkEECGoCzRX6yGLOk",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "{{local}}/postagem/1",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Criar Comentario",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjQzMTcsImV4cCI6MTYwMTI1MDcxN30.3YMoPd-7VZqGwminUZ3zZzjIC0rbeZFHVBUDEpjsSHU",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"texto\": \"Meu primeiro comentario da Higor 1\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "{{local}}/postagem/1/comentario",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem",
						"1",
						"comentario"
					]
				}
			},
			"response": []
		},
		{
			"name": "Deletar Comentario",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjM5NjQsImV4cCI6MTYwMTI1MDM2NH0.sFP_BI35UHqz-zPrcm4vb94HXuP285d7dz4Km_UIvzs",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "{{local}}/postagem/1/comentario/23",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem",
						"1",
						"comentario",
						"23"
					]
				}
			},
			"response": []
		},
		{
			"name": "Buscar Comentario",
			"request": {
				"auth": {
					"type": "bearer",
					"bearer": [
						{
							"key": "token",
							"value": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCbG9nIGRvIFRhbmdlcmlubyIsInN1YiI6IjEiLCJpYXQiOjE2MDExNjM5NjQsImV4cCI6MTYwMTI1MDM2NH0.sFP_BI35UHqz-zPrcm4vb94HXuP285d7dz4Km_UIvzs",
							"type": "string"
						},
						{
							"key": "password",
							"value": "{{token}}",
							"type": "string"
						}
					]
				},
				"method": "GET",
				"header": [],
				"url": {
					"raw": "{{local}}/postagem/1/comentario/2",
					"host": [
						"{{local}}"
					],
					"path": [
						"postagem",
						"1",
						"comentario",
						"2"
					]
				}
			},
			"response": []
		}
	],
	"protocolProfileBehavior": {}
}