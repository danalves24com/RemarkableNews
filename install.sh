#!/bin/bash
mkdir $HOME/News
mkdir $HOME/News/stories
sudo mkdir /usr/bin/.newsmark
sudo curl --request GET -sL \
     --url 'https://github.com/danalves24com/RemarkableNews/releases/download/Beta/news-fetching.jar'\
     --output '/usr/bin/.newsmark/news-fetching.jar'
sudo touch /usr/bin/newsmark
sudo echo "java -jar /usr/bin/.newsmark/news-fetching.jar" >> /usr/bin/newsmark
sudo chmod +x /usr/bin/newsmark
