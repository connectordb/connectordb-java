[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/connectordb/connectordb-java/blob/master/LICENSE) [![Build Status](https://travis-ci.org/connectordb/connectordb-java.svg?branch=master)](https://travis-ci.org/connectordb/connectordb-java)

# ConnectorDB Java Client

*WARNING: this client is currently under construction*

The client is compatible with both standard java and android. The client is used in the ConnectorDB android app to communicate with the database.

It is currently a very bare-bones client, and will be developed further subject to interest and/or needs of the android app.

## Testing

To test, you need to run ConnectorDB in test configuration:

```bash
connectordb create testdb --test
connectordb start testdb
connectordb run testdb
```

Once ConnectorDB is running, you can run tests:

```bash
gradle assemble
gradle check
```
