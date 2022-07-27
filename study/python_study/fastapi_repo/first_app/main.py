from typing import Union

from enum import Enum

from fastapi import FastAPI

class ModelName(str, Enum):
    alexnet = "alexnet"
    resnet = "resnet"
    lenet = "lenet"


firstApp = FastAPI()

@firstApp.get("/test")
def root(msg, string):
    return {"message": msg}

# 순서도 중요하다

# query string
# http://127.0.0.1:8000/items/?skip=1&limit=100
fake_items_db = [{"item_name": "Foo"}, {"item_name": "Bar"}, {"item_name": "Baz"}]


@firstApp.get("/items/")
async def read_items(skip: int = 0, limit: int = 10):
    return fake_items_db[skip : skip + limit]

# optional parameters https://fastapi.tiangolo.com/tutorial/query-params/
# req: http://127.0.0.1:8000/item/0
# response :
# // http://127.0.0.1:8000/item/0
# {
#   "item_id": 0
# }
@firstApp.get("/item/{item_id}")
async def read_item(item_id: int, q: Union[str, None]= None):
    if q:
        return {"item_id": item_id, "q": q}
    return {"item_id": item_id}

# models/
@firstApp.get("/models/{model_name}")
async def get_model(model_name: ModelName):
    if model_name == ModelName.alexnet:
        return {"model_name": model_name, "message": "Deep Learning FTW!"}

    if model_name.value == "lenet":
        return {"model_name": model_name, "message": "LeCNN all the images"}

    return {"model_name": model_name, "message": "Have some residuals"}

# path variable
@firstApp.get("/test/{msg}/{item_id}")
async def read_item(item_id: int, msg: str):
    return {msg : item_id}

# return arrray
@firstApp.get("/array")
async def read_users():
    return ["Rick", "Morty"]
