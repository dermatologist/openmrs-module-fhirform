# Dependency required for testing with spring autowired

```
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
```

## extends BaseModuleContextSensitiveTest

## Transform code

```
[
  {
    "operation": "shift",
    "spec": {
      "properties": {
        "*": {
          "linkId": {
            "*": { // match any value of keyName
              "@2": { // go back up the tree
                // now match value and metadata, but we can now
                // "reference" teh value of keyName as "&2"
                "title": "&2.title",
                "type": "&2.type",
                "required": "&2.required",
                "default": "&2.default",
                "enum": "&2.enum"
              }
            }
          }
        }
      }
    }
  }
]


```

## working

```
{
  "schema": {
    
    "questions": {
      "type": "object",

          "properties": {
            "nick": {
              "title": "Nickname",
              "type": "string",
              "required": true
            },
            "gender": {
              "title": "Gender",
              "type": "string",
              "required": false,
              "enum": [
                "male",
                "female"
              ]
            },
            "age": {
              "title": "Age",
              "type": "integer",
              "required": false
            }
          }

    }
  }
}
```