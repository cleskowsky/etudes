# Mongodb upgrader must ...

* Update secondaries first
    * Updates include mongodb itself and o/s updates
    * Some o/s updates require a reboot
* Then step down primary
* Then update old primary

# Example db.hello() reply

```json
rs0 [direct: primary] test> db.hello()
{
  topologyVersion: {
    processId: ObjectId('6953d3cb3a60818dc20cc332'),
    counter: Long('14')
  },
  hosts: [ 'b6ff3251addd:27017', 'db2:27017' ],
  setName: 'rs0',
  setVersion: 9,
  isWritablePrimary: true,
  secondary: false,
  primary: 'b6ff3251addd:27017',
  me: 'b6ff3251addd:27017',
  electionId: ObjectId('7fffffff0000000000000001'),
  lastWrite: {
    opTime: { ts: Timestamp({ t: 1767102361, i: 1 }), t: Long('1') },
    lastWriteDate: ISODate('2025-12-30T13:46:01.000Z'),
    majorityOpTime: { ts: Timestamp({ t: 1767102361, i: 1 }), t: Long('1') },
    majorityWriteDate: ISODate('2025-12-30T13:46:01.000Z')
  },
  maxBsonObjectSize: 16777216,
  maxMessageSizeBytes: 48000000,
  maxWriteBatchSize: 100000,
  localTime: ISODate('2025-12-30T13:46:05.800Z'),
  logicalSessionTimeoutMinutes: 30,
  connectionId: 117,
  minWireVersion: 0,
  maxWireVersion: 27,
  readOnly: false,
  ok: 1,
  '$clusterTime': {
    clusterTime: Timestamp({ t: 1767102361, i: 1 }),
    signature: {
      hash: Binary.createFromBase64('xHLMeZQYXfsFkKYYyeUcZXyDOz0=', 0),
      keyId: Long('7589643627953913862')
    }
  },
  operationTime: Timestamp({ t: 1767102361, i: 1 })
}
```
