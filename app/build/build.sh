timer=`date +"%Y-%m-%d %H:%M:%S"`

git pull
git add .
git commit -am "$timer"

git push
pnpm run build:h5