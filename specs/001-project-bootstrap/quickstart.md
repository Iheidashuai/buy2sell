# Quickstart: Project Bootstrap

## Prerequisites

- JDK 11
- curl or wget, used by `mvnw` to download Maven Wrapper if needed

## Verify

```bash
./mvnw -v
./mvnw clean verify
```

## Run Demo Main

```bash
./mvnw -pl buy2sell-bootstrap -am test
./mvnw -pl buy2sell-bootstrap -am exec:java -Dexec.mainClass=com.buy2sell.bootstrap.Buy2SellApplication
```

The second command requires adding `exec-maven-plugin` in a future plan. For now, prefer tests as executable verification.
