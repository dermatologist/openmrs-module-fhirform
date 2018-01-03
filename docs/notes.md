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

## BaseOpenMrs Object requires the following in Liquibase
```
            <column name="changed_by" type="varchar(255)"/>
            <column name="date_created" type="datetime"/>
            <column name="creator" type="varchar(255)"/>
            <column name="date_changed" type="datetime"/>
            <column name="voided" type="bit"/>
            <column name="voided_by" type="char(38)"/>
            <column name="date_voided" type="datetime"/>
            <column name="void_reason" type="varchar(255)"/>

```

## @Transactional annotation is required.
* Will throw HibernateEventListner NullPointerException.