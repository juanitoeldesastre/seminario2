import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

function ScrollToTop() {
    const { pathname } = useLocation();

    useEffect(() => {
        window.scrollTo({ top: 0, behavior: 'smooth' });

        const handleClick = (e) => {
            const enlace = e.target.closest('a');
            if (enlace && enlace.getAttribute('href') === pathname) {
                window.scrollTo({ top: 0, behavior: 'auto' });
            }
        };

        document.addEventListener('click', handleClick);

        return () => {
            document.removeEventListener('click', handleClick);
        };
    }, [pathname]);

    return null;
}

export default ScrollToTop;
