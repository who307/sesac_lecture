import styles from '../app/page.module.css'
import Navbar from './Navbar'

export default function Header(){
    return (
        <>
            <header>
                <h1 className={styles.title}> Header 입니다.</h1>
            </header>
        </>
    )
}