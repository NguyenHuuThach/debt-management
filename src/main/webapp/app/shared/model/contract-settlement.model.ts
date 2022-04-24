export interface IContractSettlement {
  id?: number;
  contractId?: number;
  settlerName?: string | null;
}

export const defaultValue: Readonly<IContractSettlement> = {};
