package bookStore

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method 'GET'
    url '/api/book/1000'
    headers {}
  }
response {
  status 200
  body("""
  {
    "id": 1000,
    "name": "test book",
    "writter": "test wrtter",
    "price": 102.5
  }
  """)
  headers {
    header('Content-Type': 'application/json;charset=UTF-8')
  }
 }
}