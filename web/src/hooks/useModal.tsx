
import { VxeUI } from 'vxe-pc-ui'

export function showModal(dom: any, config: any) {
  VxeUI.modal.open({
    mask: false,
    lockView: false,
    escClosable: true,
    width: 600,
    height: 400,
    showZoom: true,
    resize: true,
    showFooter: true,
    showConfirmButton: true,
    confirmButtonText: '确认',
    showCancelButton: true,
    cancelButtonText: '取消',
    ...config,
    slots: {
      default () {
        return <>{dom}</>
      }
    }
  })

}
