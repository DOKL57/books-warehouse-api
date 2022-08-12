# books-warehouse-api

[![License: WTFPL](https://img.shields.io/badge/License-WTFPL-brightgreen.svg)](http://www.wtfpl.net/about/)  
API to automate the accounting of goods in the warehouse of the bookstore 

## Running the application locally

```shell
git https://github.com/DOKL57/books-warehouse-api.git
cd ./books-warehouse-api
docker-compose up -d
mvn spring-boot:run
```



## Explore Rest APIs

The app defines following CRUD APIs.


### Genre

| Method | Url               | Decription       | 
|--------|-------------------|------------------|
| POST   | /api/genre/create | Create new genre |

### Book

| Method | Url                        | Decription                       | 
|--------|----------------------------|----------------------------------|
| POST   | /api/book/income           | Add a new income                 |
| POST   | /api/book/outcome          | Add a new outcome                |
| GET    | /api/book/getBooksByParams | Get all incoming books by params |