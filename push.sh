BRANCH='deployment'$(date +"%Y%m%d%H%M")
REMOTE='openshift'
echo 'Switch to master.'
git checkout master
echo "Create branch '$BRANCH'."
git branch $BRANCH
echo "Switch to $BRANCH."
git checkout $BRANCH
echo "Make changes."
cp "local/web.xml" "src/main/webapp/WEB-INF/web.xml"
git add "src/main/webapp/WEB-INF/web.xml"
echo "Make commit."
git commit -am "$BRANCH"
echo "Deploy to '$REMOTE'."
git push -uf $REMOTE $BRANCH:master
git checkout master