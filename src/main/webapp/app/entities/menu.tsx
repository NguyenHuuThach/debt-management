import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/contract">
        Contract
      </MenuItem>
      <MenuItem icon="asterisk" to="/interest-paid">
        Interest Paid
      </MenuItem>
      <MenuItem icon="asterisk" to="/pay-down-principal">
        Pay Down Principal
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-settlement">
        Contract Settlement
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-history">
        Contract History
      </MenuItem>
      <MenuItem icon="asterisk" to="/interest-paid-history">
        Interest Paid History
      </MenuItem>
      <MenuItem icon="asterisk" to="/pay-down-principal-history">
        Pay Down Principal History
      </MenuItem>
      <MenuItem icon="asterisk" to="/contract-settlement-history">
        Contract Settlement History
      </MenuItem>
      <MenuItem icon="asterisk" to="/interest-pay">
        Interest Pay
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
