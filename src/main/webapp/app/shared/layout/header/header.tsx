// import './header.scss';
//
// import React, { useState } from 'react';
//
// import { Navbar, Nav, NavbarToggler, Collapse } from 'reactstrap';
// import LoadingBar from 'react-redux-loading-bar';
//
// import { Home, Brand } from './header-components';
// import { AdminMenu, EntitiesMenu, AccountMenu } from '../menus';
//
// export interface IHeaderProps {
//   isAuthenticated: boolean;
//   isAdmin: boolean;
//   ribbonEnv: string;
//   isInProduction: boolean;
//   isOpenAPIEnabled: boolean;
// }
//
// const Header = (props: IHeaderProps) => {
//   const [menuOpen, setMenuOpen] = useState(false);
//
//   const renderDevRibbon = () =>
//     props.isInProduction === false ? (
//       <div className="ribbon dev">
//         <a href="">Development</a>
//       </div>
//     ) : null;
//
//   const toggleMenu = () => setMenuOpen(!menuOpen);
//
//   /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */
//
//   return (
//     <div id="app-header">
//       {renderDevRibbon()}
//       <LoadingBar className="loading-bar" />
//       <Navbar data-cy="navbar" dark expand="md" fixed="top" className="bg-dark">
//         <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
//         <Brand />
//         <Collapse isOpen={menuOpen} navbar>
//           <Nav id="header-tabs" className="ms-auto" navbar>
//             <Home />
//             {props.isAuthenticated && <EntitiesMenu />}
//             {props.isAuthenticated && props.isAdmin && <AdminMenu showOpenAPI={props.isOpenAPIEnabled} />}
//             <AccountMenu isAuthenticated={props.isAuthenticated} />
//           </Nav>
//         </Collapse>
//       </Navbar>
//     </div>
//   );
// };
//
// export default Header;

import './header.scss';

import React, { useState } from 'react';

import { Navbar, Nav, NavbarToggler, Collapse } from 'reactstrap';
import LoadingBar from 'react-redux-loading-bar';

import { Home, Brand } from './header-components';
import { AdminMenu, EntitiesMenu, AccountMenu } from '../menus';
import Notification from '../../../entities/components/Notification';

export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
}

const Header = (props: IHeaderProps) => {
  const [menuOpen, setMenuOpen] = useState(false);

  const renderDevRibbon = () =>
    props.isInProduction === false ? (
      <div className="ribbon dev">
        <a href="">Development</a>
      </div>
    ) : null;

  const toggleMenu = () => setMenuOpen(!menuOpen);

  /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */

  return (
    <nav className="main-header navbar navbar-expand navbar-white navbar-light" id="app-header">
      <ul className="navbar-nav">
        <li className="nav-item">
          <a className="nav-link" data-widget="pushmenu" href="#" role="button">
            <i className="fas fa-bars" />
          </a>
        </li>
      </ul>
      <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
      <Collapse isOpen={menuOpen} navbar className="justify-content-end">
        <Nav id="header-tabs" className="ms-auto" navbar>
          <Notification isAuthenticated={props.isAuthenticated} />
          {/* <Home /> */}
          {/* {props.isAuthenticated && <EntitiesMenu />} */}
          {props.isAuthenticated && props.isAdmin && <AdminMenu showOpenAPI={props.isOpenAPIEnabled} />}
          <AccountMenu isAuthenticated={props.isAuthenticated} />
        </Nav>
      </Collapse>
    </nav>
  );
};

export default Header;
