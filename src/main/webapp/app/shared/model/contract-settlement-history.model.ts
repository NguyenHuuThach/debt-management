export interface IContractSettlementHistory {
  id?: number;
  contractId?: number;
  settlerName?: string | null;
}

export const defaultValue: Readonly<IContractSettlementHistory> = {};
