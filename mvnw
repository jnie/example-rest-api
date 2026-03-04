#!/bin/sh
# Maven Wrapper startup script for Unix

# Resolve links
PRG="$0"
while [ -h "$PRG" ]; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done

SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Maven"
APP_BASE_NAME=`basename "$0"`

# Helper function to concat lines in a file (must be defined before use)
concat_lines() {
    if [ -f "$1" ]; then
        cat "$1" | tr -s '\n' ' '
    fi
}

# Add default JVM options
DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"

# Get Java command
if [ -n "$JAVA_HOME" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ]; then
        echo "Error: JAVA_HOME is set to an invalid directory." >&2
        echo "Please set the JAVA_HOME variable in your environment to match the" >&2
        echo "location of your Java installation." >&2
        exit 1
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || { echo "Error: JAVA_HOME is not set and no 'java' command could be found." >&2; exit 1; }
fi

# Maven wrapper jar
MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$(dirname "$0")}"
MAVEN_OPTS="$(concat_lines "$MAVEN_PROJECTBASEDIR/.mvn/jvm.config") $MAVEN_OPTS"

# Set maven.multiModuleProjectDirectory for Maven Wrapper 3.x
MAVEN_OPTS="$MAVEN_OPTS -Dmaven.multiModuleProjectDirectory=$MAVEN_PROJECTBASEDIR"

exec "$JAVACMD" \
    $DEFAULT_JVM_OPTS \
    $MAVEN_OPTS \
    "-Dorg.gradle.appname=$APP_BASE_NAME" \
    -classpath "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar" \
    org.apache.maven.wrapper.MavenWrapperMain \
    "$@"
