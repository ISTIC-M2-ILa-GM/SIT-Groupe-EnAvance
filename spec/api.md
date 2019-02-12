## API

* GET /api/mission/last

Result:
```
    {
        id: ...,
        points: [
            {index: int, x: double, y: double, z: double},
            {index: int, x: double, y: double, z: double},
            {index: int, x: double, y: double, z: double}
        ]
    }
```

* POST /api/mission/{mission_id}/picture/{point_index}

Result:
```
    <<PICTURE_BINARY_DATA>>
```