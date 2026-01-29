
FROM postgres:17-alpine
COPY init.sql /docker-entrypoint-initdb.d/

LABEL authors="zomby"

ENTRYPOINT ["top", "-b"]