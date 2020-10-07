#!/bin/sh

echo "The application will start in ${PG_SLEEP}s..." && sleep ${PG_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "aust.iums.pg.admission.PgAdmissionApplication"  "$@"
