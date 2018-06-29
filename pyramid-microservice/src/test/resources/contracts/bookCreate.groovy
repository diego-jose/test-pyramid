package bookStore

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {

    method 'POST'
    url '/api/book'
    headers {
      header('Content-Type': 'application/json;charset=UTF-8')
    }
    body("""
    {
      "name": "test INSERT",
      "price": 102.5,
      "writter": "test writter",
    }
  """)
  }
  response {
    status 201
    headers {
      header('Content-Type': 'application/json;charset=UTF-8')
    }

  }
}