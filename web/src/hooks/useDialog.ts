import Dialog from "@/components/Dialog/index.vue";

export function useDialog() {
  return new Promise(() => {
    const mountNode = document.createElement("div");

    let dialogApp: any = createApp(Dialog);

    document.body.appendChild(mountNode);

    dialogApp.mount(mountNode);
  });
}
