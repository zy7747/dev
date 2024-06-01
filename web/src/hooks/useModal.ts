import ShowModal from "@/components/ShowModal/index.vue";

export function showModal(dom: any, config: any) {

  return new Promise(() => {
    const div = document.createElement("div");

    let dialogApp: any = createApp(ShowModal,{...config,dom});

    document.body.appendChild(div);

    dialogApp.mount(div);
  });
}
