git add -f html/build/dist
git commit -m "Web deploy"
git push
git subtree push --prefix html/build/dist origin gh-pages

