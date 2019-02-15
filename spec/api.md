## API
* POST /api/mission

Result:
```
    {
        points: [
            {index: int, x: double, y: double, z: double},
            {index: int, x: double, y: double, z: double},
            {index: int, x: double, y: double, z: double}
        ]
    }
```

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

* POST /api/mission/{mission_id}/result

Result:
```
    {
        picture: <<BASE 64>>,
        point: {x: double, y: double, z: double}
    }
```