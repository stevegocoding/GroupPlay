#!/bin/bash

tomcat_home=$TOMCAT_HOME
ant_home=$ANT_HOME
svnsrcurl=http://10.1.5.8/RCS/src/trunk/server/padata/

if [ $# != 2 ] ; then
echo "=======Build Information==========";
echo "USAGE: $0 PROJECT PLATFORM[required:client|server|portal]"
echo " e.g.: sh build.sh padata portal"
echo "==================================";
exit 1;
fi

if [ ! -x javadoc ] ; then
mkdir javadoc
fi


project=$1
platform=$2

svnfile="$svnsrcurl/src/javadoc/$platform/build_$platform.xml";
buildxml="build_doc-$project-$platform.xml"

echo "=======Build Information==========";
echo "Project   : $project";
echo "Platform  : $platform";
echo "SVNFile   : $svnfile";
echo "BuildFile : $buildxml";
rm -fr project/$project;
svn export $svnfile $buildxml;
echo "==================================";


echo "=============Start Build =========";
ant -buildfile $buildxml  -Dtomcat-home=$tomcat_home -Dant-home=$ant_home -Dsvnsrcurl=$svnsrcurl
rm -fr $buildxml
echo "==================================";