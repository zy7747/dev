import { VxeUI } from "vxe-pc-ui";

export function showModal(Dom: any, config: any, prop?: any) {
  const DomRef = ref();

  VxeUI.modal.open({
    mask: false,
    lockView: false,
    escClosable: true,
    width: 600,
    showZoom: true,
    resize: true,
    showFooter: true,
    showConfirmButton: true,
    confirmButtonText: "确认",
    showCancelButton: true,
    cancelButtonText: "取消",
    ...config,
    slots: {
      default() {
        return <Dom {...prop} ref={DomRef} />;
      },
    },
    onConfirm() {
      if (unref(DomRef).confirm) {
        unref(DomRef).confirm();
      }
    },
    onCancel() {
      if (unref(DomRef).cancel) {
        unref(DomRef).cancel();
      }
    },
  });
}

export function closeModal() {
  VxeUI.modal.close();
}
