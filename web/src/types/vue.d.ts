declare module "*.vue" {
  import { ComponentOptions } from "vue";
  const componentOptions: ComponentOptions;
  export default componentOptions;
}

declare interface Window {
  _AMapSecurityConfig: any;
  Loca: any;
}
