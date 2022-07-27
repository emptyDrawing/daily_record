from typing import Union

from pydantic import BaseModel

from fastapi import FastAPI

postApp = FastAPI();

# post는 Pydantic 을 쓴다
# 일단 파일 나눠서 실행하는 법을 모르니 여기까지만 해보고 추후 더 확인해보낟.

class Item(BaseModel):
    name: str
    description: Union[str, None] = None
    price: float
    tax: Union[float, None] = None


@postApp.post("/items/")
async def create_item(item: Item):
    return item