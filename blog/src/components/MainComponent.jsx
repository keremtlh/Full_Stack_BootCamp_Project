// rfce
import { withTranslation } from 'react-i18next'
import { Link } from 'react-router-dom/dist'

// FUNCTION COMPONENT MAIN
function MainComponent() {
  return (
    <>
    <br /><br /><br />
    <h1 style={{ textAlign: 'center' }}><b><big>TODOLIST</big></b> </h1>
    <br /><br />
    <p style={{ textAlign: 'center' }}>Uygulamasına hoşgeldiniz. İstediğiniz konu hakkında liste oluşturabilirsiniz.</p>
    <br /><br />
    <div style={{ textAlign: 'center' }}> {/* Link'i içeren bir div */}
    <Link to="/blog/category/list" className="btn btn-primary">Liste Sayfası İçin Tıkalyınız</Link>
  </div>
  <br /><br /><br />
   </>
  )
}

// Export
export default withTranslation()(MainComponent)