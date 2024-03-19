import { router } from "@/router";
import NProgress from "nprogress";
import "nprogress/nprogress.css";

router.beforeEach(async (_to, _from, next) => {
  NProgress.start();
  next();
});

router.afterEach(() => {
  NProgress.done();
});
