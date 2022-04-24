// import './footer.scss';
//
// import React from 'react';
//
// import { Col, Row } from 'reactstrap';
//
// const Footer = () => (
//   <div className="footer page-content">
//     <Row>
//       <Col md="12">
//         <p>Your footer</p>
//       </Col>
//     </Row>
//   </div>
// );
//
// export default Footer;



// import './footer.scss';

import React from 'react';

// import { Col, Row } from 'reactstrap';

const Preloader = () => (
  <div className="preloader flex-column justify-content-center align-items-center">
        <img
          className="animation__shake"
          src="content/dist/img/AdminLTELogo.png"
          alt="AdminLTELogo"
          height={60}
          width={60}
        />
      </div>
);

export default Preloader;
