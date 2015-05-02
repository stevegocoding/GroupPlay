#!/bin/bash

export TOMCAT_HOME=/home/cmcc/programe/apache-tomcat-7.0.54_ems
export ANT_HOME=/home/cmcc/autobuild/Ant_Maven/bin-ant-1.9.4
tomcat_home=$TOMCAT_HOME
ant_home=$ANT_HOME
svnsrcurl=http://218.206.178.27/RCS/src/im/server/usercenter/

if [ $# != 2 ] ; then
echo "=======Build Information==========";
echo "USAGE: $0 PROJECT PLATFORM[required:dev|test|stage|release]"
echo " e.g.: sh build.sh usercenter test"
echo "==================================";
exit 1;
fi

if [ ! -x project ] ; then
mkdir project
fi


project=$1
platform=$2

svnfile="$svnsrcurl/src/properties/$platform/build_$platform.xml";
buildxml="build-$project-$platform.xml"

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