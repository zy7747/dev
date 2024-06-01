import Dialog from "@/components/Dialog/index.vue";

export function showDialog(config: any) {
  return new Promise(() => {
    const div = document.createElement("div");

    let dialogApp: any = createApp(Dialog, config);

    document.body.appendChild(div);

    dialogApp.mount(div);
  });
}
